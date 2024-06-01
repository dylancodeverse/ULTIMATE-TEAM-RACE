let sidebar = document.querySelector(".sidebar");
let closeBtn = document.querySelector("#btn");
let logo = document.querySelector(".logo")
closeBtn.addEventListener("click", ()=>{
  sidebar.classList.toggle("open");
  menuBtnChange();//calling the function(optional)
});


// following are the code to change sidebar button(optional)
function menuBtnChange() {
 if(sidebar.classList.contains("open")){
   closeBtn.classList.replace("ti-menu-2", "ti-menu");//replacing the iocns class
   logo.setAttribute("width","160")
 }else {
   closeBtn.classList.replace("ti-menu","ti-menu-2");//replacing the iocns class
   logo.setAttribute("width","0")
 }
  
}


  // Récupère l'URL de la page actuelle
  var currentLocation = window.location.href;

  // Récupère tous les liens de la barre latérale
  var navLinks = document.querySelectorAll('.nav-list a');
  console.log(currentLocation)
  // Parcourt chaque lien
  navLinks.forEach(function(navLink) {
    // Vérifie si l'URL de la page correspond à l'URL du lien
    console.log(navLink.href)
    if (navLink.href === currentLocation) {
      // Ajoute la classe "active" à l'élément parent du lien
      navLink.parentElement.classList.add('active');
    }
  });
