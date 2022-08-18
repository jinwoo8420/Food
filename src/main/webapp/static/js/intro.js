document.addEventListener("DOMContentLoaded", () => {
  const div_menu = document.querySelector("div.list_group");

  div_menu?.addEventListener("click", (e) => {
    const button = e.target;

    if (button.tagName === "BUTTON") {
      const mapx = button.dataset.mapx;
      const mapy = button.dataset.mapy;
      const dust = button.dataset.dust;

      console.log(mapx, mapy, dust);
      //   console.log(`${rootPath}/detail/${mapx},${mapy}/list`);
      document.location.href = `${rootPath}/detail/${mapx},${mapy},${dust}/list`;
    }
  });
});
