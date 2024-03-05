Feature: Verificar la funcionalidad de la pagian de login

  Background:
    Given me encuentro en la pagina home
    When hago click en el boton de login

  Scenario: Introducir los datos invalidos
    And introduzco el email "<email>" y la contrasena "<password>"
    Then se muestra el mensaje de error "Warning: No match for E-Mail Address and/or Password."

    Examples:
      | email                | password     |
      | ivangomcav.com       | Boligrafo_12 |
      | ivangomcav@gmail.com | pepepe       |
      | .com                 | pepepe       |

  Scenario: Introducir los datos correctos
    And introduzco el email "ivangomcav@gmail.com" y la contrasena "Boligrafo_12"
    Then me encuentro en la pagina de MyAccount "https://opencart.abstracta.us/index.php?route=account/account"