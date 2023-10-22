# Santander Dev Week 2023
Java RESTful API criada para a Santander Dev Week

## Diagrama de Classes

```mermaid
classDiagram
  class User {
    - name: String
    - account: Account
    - features: Feature[]
    - creditCard: CreditCard
    - hints: Hint[]
  }

  class Account {
    - Agency: String
    - Number: String
    - Balance: double
    - Limit: double
  }

  class Feature {
    - icon: String
    - description: String
  }

  class CreditCard {
    - Number: String
    - Limit: double
  }

  class Hint {
    - icon: String
    - description: String
  }

  User "1" *-- "1" Account
  User "1" *-- "N" Feature
  User "1" *-- "1" CreditCard
  User "1" *-- "N" Hint
```

