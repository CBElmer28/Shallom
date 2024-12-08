<div class="modal fade" id="configModal" tabindex="-1" aria-labelledby="configVentaModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="configVentaModalLabel">Modificar Estado</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="configVentaForm" action="/webbs/configVenta" method="POST">
          <!-- Campo oculto para enviar el ID -->
          <input type="hidden" id="ventaId" name="id">

          <!-- Campo desplegable para seleccionar el estado -->
          <div class="mb-3">
            <label for="estado" class="form-label">Estado</label>
            <select class="form-select" id="estado" name="estado" required>
              <option value="Entregado">Entregado</option>
              <option value="Pendiente">Pendiente</option>
            </select>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="background-color:#5c626e ">Cancelar</button>
        <button type="submit" class="btn btn-primary" form="configVentaForm" style="background-color: #1c7430">Guardar Cambios</button>
      </div>
    </div>
  </div>
</div>


<script>
  const configModal = document.getElementById('configModal');
  configModal.addEventListener('show.bs.modal', function (event) {
    // Botón que disparó el modal
    const button = event.relatedTarget;
    const id = button.getAttribute('data-id');
    const estado = button.getAttribute('data-estado');

    // Cargar los datos en el modal
    const ventaIdInput = document.getElementById('ventaId');
    const estadoSelect = document.getElementById('estado');

    ventaIdInput.value = id;
    estadoSelect.value = estado;
  });
</script>
