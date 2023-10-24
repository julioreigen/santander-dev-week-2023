package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import me.dio.service.exception.BusinessException;
import me.dio.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {

    private final static Long USER_ID = 1L;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User create(User userToCreate) {

        ofNullable(userToCreate).orElseThrow(() -> new BusinessException("User must not be null."));
        ofNullable(userToCreate.getAccount()).orElseThrow(() -> new BusinessException("User account must not be null."));
        ofNullable(userToCreate.getCreditCard()).orElseThrow(() -> new BusinessException("User credit card must not be null"));

        this.validateChangeableId(userToCreate.getId(), "created");

        String accountNumber = userToCreate.getAccount().getNumber();
        String ccNumber = userToCreate.getCreditCard().getNumber();

        if (userRepository.existsByAccountNumber(accountNumber)) {
            throw new BusinessException("This account number already exists.");
        }
        if (userRepository.existsByCreditCardNumber(ccNumber)) {
            throw new BusinessException("This credit card number already exists.");
        }

        return userRepository.save(userToCreate);
    }

    @Transactional
    public User update(Long id, User userToUpdate) {
        this.validateChangeableId(id, "updated");
        User existingUser = this.findById(id);

        if (!userToUpdate.getId().equals(existingUser.getId())) {
            throw new BusinessException("It is not possible to change the ID of the users!");
        }

        existingUser.setAccount(userToUpdate.getAccount());
        existingUser.setName(userToUpdate.getName());
        existingUser.setCreditCard(userToUpdate.getCreditCard());
        existingUser.setHints(userToUpdate.getHints());
        existingUser.setFeatures(userToUpdate.getFeatures());

        return this.userRepository.save(existingUser);
    }

    @Transactional
    public void delete(Long id) {
        validateChangeableId(id, "deleted");
        User existingUser = this.findById(id);
        userRepository.delete(existingUser);
    }

    private void validateChangeableId(Long id, String operation) {
        if (USER_ID.equals(id)) {
            throw new BusinessException("User with ID %d can not be %s.".formatted(USER_ID, operation));
        }
    }
}
