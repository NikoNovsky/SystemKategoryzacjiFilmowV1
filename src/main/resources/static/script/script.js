document.addEventListener("DOMContentLoaded", () => {
  const bootstrapScript = document.createElement("script");
  bootstrapScript.src = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js";
  bootstrapScript.integrity = "sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm";
  bootstrapScript.crossOrigin = "anonymous";

  document.body.appendChild(bootstrapScript);

  const table = document.getElementById("movieTable");
  const sortableHeaders = [...document.querySelectorAll("th[data-column]")];

  let currentColumn = null;
  let sortAsc = true;

  sortableHeaders.forEach(header => {
    header.addEventListener("click", () => {
      const columnIndex = Number(header.dataset.column)
      if (currentColumn === columnIndex) {
        sortAsc = !sortAsc;
      } else {
        currentColumn = columnIndex;
        sortAsc = true;
      }
      sortTable(columnIndex, sortAsc);
      updateIcons(header, sortAsc);
    });
  });

  function sortTable(columnIndex, asc) {
    const body = table.tBodies[0];
    const rows = Array.from(body.rows);

    rows.sort((a, b) => {
      let valA = a.cells[columnIndex].querySelector("input")?.value.trim() ?? "";
      let valB = b.cells[columnIndex].querySelector("input")?.value.trim() ?? "";

      if (!isNaN(valA) && !isNaN(valB)) {
        return asc ? valA - valB : valB - valA;
      }

      return asc
        ? valA.localeCompare(valB, "pl")
        : valB.localeCompare(valA, "pl");
    });

    rows.forEach(row => body.appendChild(row));
  }

  function updateIcons(activeHeader, asc) {
    sortableHeaders.forEach(h => {
      h.classList.remove("active");
      h.querySelector("span").textContent = "";
    });

    activeHeader.classList.add("active");
    activeHeader.querySelector("span").textContent = asc ? "▲" : "▼";
  }
});