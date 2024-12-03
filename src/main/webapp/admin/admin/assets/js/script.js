// Seleccionar todos los elementos del menú lateral
const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');

// Añadir eventos de clic a cada elemento del menú lateral
allSideMenu.forEach(item => {
    const li = item.parentElement;

    item.addEventListener('click', () => {
        allSideMenu.forEach(i => {
            i.parentElement.classList.remove('active');
        });
        li.classList.add('active');
    });
});

// TOGGLE SIDEBAR
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('sidebar');
const content = document.getElementById('content');

// Alternar la clase 'hide' en el sidebar y ajustar el contenido
menuBar.addEventListener('click', () => {
    sidebar.classList.toggle('hide');
});

// Manejar la barra de búsqueda
const searchButton = document.querySelector('#content nav form .form-input button');
const searchButtonIcon = document.querySelector('#content nav form .form-input button .bx');
const searchForm = document.querySelector('#content nav form');

searchButton.addEventListener('click', function (e) {
    if (window.innerWidth < 576) {
        e.preventDefault();
        searchForm.classList.toggle('show');
        searchButtonIcon.classList.toggle('bx-x');
        searchButtonIcon.classList.toggle('bx-search');
    }
});

// Ajustes iniciales para la vista móvil
if (window.innerWidth < 768) {
    sidebar.classList.add('hide');
    content.style.width = 'calc(100% - 60px)';
    content.style.left = '60px';
} else if (window.innerWidth > 576) {
    searchButtonIcon.classList.replace('bx-x', 'bx-search');
    searchForm.classList.remove('show');
}

// Reaccionar al cambio de tamaño de la ventana
window.addEventListener('resize', () => {
    if (window.innerWidth > 576) {
        searchButtonIcon.classList.replace('bx-x', 'bx-search');
        searchForm.classList.remove('show');
    }
});

// Cambiar a modo oscuro
const switchMode = document.getElementById('switch-mode');
switchMode.addEventListener('change', function () {
    document.body.classList.toggle('dark', this.checked);
});