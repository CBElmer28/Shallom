<div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addProductModalLabel">Agregar Nuevo Producto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="addProductForm" action="/webbs/agregar-producto" method="POST" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="productName" class="form-label">Nombre del Producto</label>
                        <input type="text" class="form-control" id="productName" name="nombre" required>
                    </div>
                    <div class="mb-3">
                        <label for="productCategory" class="form-label">Categoría</label>
                        <input type="text" class="form-control" id="productCategory" name="cat" required>
                    </div>
                    <div class="mb-3">
                        <label for="productPrice" class="form-label">Precio</label>
                        <input type="number" class="form-control" id="productPrice" name="precio" required>
                    </div>
                    <div class="mb-3">
                        <label for="productStock" class="form-label">Stock</label>
                        <input type="number" class="form-control" id="productStock" name="stock" required>
                    </div>
                    <div class="mb-3">
                        <label for="producprovee" class="form-label">proveedor</label>
                        <input type="number" class="form-control" id="producprovee" name="id_proveedor" name="stock" required>
                    </div>
                    <div class="mb-3">
                        <label for="productImage" class="form-label">Imagen del Producto</label>
                        <input type="file" name="imagen" class="form-control" id="productImage" accept="image/*" required>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="background-color:#5c626e ">Cancelar</button>
                <button type="submit" class="btn btn-primary" form="addProductForm" style="background-color:#007bff">Guardar Producto</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteProductModal" tabindex="-1" aria-labelledby="deleteProductModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteProductModalLabel">Confirmar Eliminacion</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p> seguro de que deseas eliminar este producto?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="background-color:#5c626e ">Cancelar</button>
                <form id="deleteProductForm" action="/webbs/eliminar-producto" method="POST">
                    <input type="hidden" id="deleteProductId" name="id">
                    <button type="submit" class="btn btn-danger" style="background-color: red">Eliminar</button>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="editProductModal" tabindex="-1" aria-labelledby="editProductModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editProductModalLabel">Modificar Producto</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="editProductForm" action="/webbs/modificar-producto" method="POST" enctype="multipart/form-data">
                    <input type="hidden" id="editProductId" name="id">
                    <div class="mb-3">
                        <label for="editProductName" class="form-label">Nombre del Producto</label>
                        <input type="text" class="form-control" id="editProductName" name="nombre" required>
                    </div>
                    <div class="mb-3">
                        <label for="editProductCategory" class="form-label">Categoría</label>
                        <input type="text" class="form-control" id="editProductCategory" name="cat" required>
                    </div>
                    <div class="mb-3">
                        <label for="editProductPrice" class="form-label">Precio</label>
                        <input type="number" class="form-control" id="editProductPrice" name="precio" required>
                    </div>
                    <div class="mb-3">
                        <label for="editProductStock" class="form-label">Stock</label>
                        <input type="number" class="form-control" id="editProductStock" name="stock" required>
                    </div>
                    <div class="mb-3">
                        <label for="editProductImage" class="form-label">Imagen del Producto</label>
                        <input type="file" class="form-control" id="editProductImage" name="imagen" accept="image/*">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="submit" class="btn btn-primary" form="editProductForm">Guardar Cambios</button>
            </div>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        const deleteButtons = document.querySelectorAll(".btn.eliminar");
        const deleteProductIdInput = document.getElementById("deleteProductId");

        deleteButtons.forEach(button => {
            button.addEventListener("click", function () {
                const productId = this.dataset.productId; // ID del producto pasado en el botón
                deleteProductIdInput.value = productId;
            });
        });
    });
</script>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        const editButtons = document.querySelectorAll(".btn.modificar");
        const editProductModal = document.getElementById("editProductModal");

        editButtons.forEach(button => {
            button.addEventListener("click", function () {
                const id = this.dataset.productId;
                const name = this.dataset.productName;
                const category = this.dataset.productCategory;
                const price = this.dataset.productPrice;
                const stock = this.dataset.productStock;

                document.getElementById("editProductId").value = id;
                document.getElementById("editProductName").value = name;
                document.getElementById("editProductCategory").value = category;
                document.getElementById("editProductPrice").value = price;
                document.getElementById("editProductStock").value = stock;
            });
        });
    });
</script>
