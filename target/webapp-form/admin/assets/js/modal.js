// Abre el modal de modificar producto
        function openModifyModal(nombre, precio, cantidad) {
            document.getElementById('modifyModal').style.display = 'block';
            document.getElementById('nombre').value = nombre;
            document.getElementById('precio').value = precio;
            document.getElementById('cantidad').value = cantidad;
        }

        // Cierra el modal de modificar producto
        function closeModifyModal() {
            document.getElementById('modifyModal').style.display = 'none';
        }

        // Abre el modal de agregar producto
        function openAddModal() {
            document.getElementById('addModal').style.display = 'block';
        }

        // Cierra el modal de agregar producto
        function closeAddModal() {
            document.getElementById('addModal').style.display = 'none';
        }

        // Cerrar los modales si se hace clic fuera de ellos
        window.onclick = function(event) {
            var modifyModal = document.getElementById('modifyModal');
            var addModal = document.getElementById('addModal');
            if (event.target == modifyModal) {
                modifyModal.style.display = 'none';
            }
            if (event.target == addModal) {
                addModal.style.display = 'none';
            }
        }