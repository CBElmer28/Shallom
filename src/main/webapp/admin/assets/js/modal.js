function openModal(nombre, precio, cantidad) {
    document.getElementById('modal').style.display = 'block';
    document.getElementById('nombre').value = nombre;
    document.getElementById('precio').value = precio;
    document.getElementById('cantidad').value = cantidad;
}

// Cierra el modal
function closeModal() {
    document.getElementById('modal').style.display = 'none';
}

// Cerrar modal si se hace clic fuera de él
window.onclick = function(event) {
    var modal = document.getElementById('modal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}