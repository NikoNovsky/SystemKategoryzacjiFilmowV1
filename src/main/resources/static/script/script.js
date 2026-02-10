const bootstrapScript = document.createElement("script");
bootstrapScript.src = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js";
bootstrapScript.integrity = "sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm";
bootstrapScript.crossOrigin = "anonymous";

//let tableAndForms = document.getElementById("tableAndForms");
//
//document.getElementById("loadAllMovies").addEventListener("click", () => {
//    if (tableAndForms.style.display === "none") {
//        tableAndForms.style.display = "block";
//    }
//});

document.body.appendChild(bootstrapScript);