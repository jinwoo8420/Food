document.addEventListener("DOMContentLoaded", () => {
  const div_menu = document.querySelector("div.btn");

  div_menu?.addEventListener("click", (e) => {
    const button = e.target;

    if (button.tagName === "BUTTON") {
      const mapx = button.dataset.mapx;
      const mapy = button.dataset.mapy;
      const dust = button.dataset.dust;

      document.location.href = `${rootPath}/detail/${mapx},${mapy},${dust}/list`;
    }
  });
});
