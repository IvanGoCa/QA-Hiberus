Feature: Validar funcionalidad checkout

  Background:
    Given me encuentro en la pagina de login
    When realizo el login con el user "standard_user" y el pass "secret_sauce"
    Then me encuentro en la home de la app

  Scenario: Validar comprobar el precio final
    And anado al carrito "3" productos
    And hago click en el boton del carrito
    And presiono en el boton de checkout
    And relleno los datos FirstName "Ivan" LastName "Gomez" Zip "43900"
    Then valido que el precio total del pedido es el correcto

  Scenario: Validar realizar un pedidio
    And anado al carrito "3" productos
    And hago click en el boton del carrito
    And presiono en el boton de checkout
    And relleno los datos FirstName "Ivan" LastName "Gomez" Zip "43900"
    And finalizo el checkout
    Then valido que el mensaje es "Your order has been dispatched, and will arrive just as fast as the pony can get there!"

