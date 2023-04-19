# UserMS

Usuarios: este microservicio es responsable de gestionar los usuarios, darlos 
de alta, modificar. Además de proporcionar información como puntos de 
fidelidad o compra media.

ENDPOINTS
• Endpoint alta usuario con estos campos:
o Nombre
o Apellidos
o Dirección
o Método pago (Visa, Paypal, Transferencia)
• Endpoint Update usuario dado un Id
• Endpoint Borrar usuario dado un Id
• Endpoint Buscar usuario dado un Id devuelve
o Nombre
o Apellidos
o Dirección
o Método pago (Visa, Paypal, Transferencia
o Id
o Puntos fidelidad (explicado abajo)
o Compra media (media de todos los carritos)
• Endpoint Buscar usuario dado nombre
• Endpoint lista todos los usuarios
• Endpoint para carga de lista de usuarios
• Endpoint añadir favorito id usuario + id producto = validación con catálogo que exista
• Endpoint borrar favorito
• Los puntos de fidelidad serán calculados de la siguiente manera:
Compras superior en € Puntos fidelidad
20 +1
30 +3
50 +5
100 +10
