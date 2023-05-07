<template>
  <section class="pagination">
    <div class="center">
      <div class="left-arrow">
        <a href="#"><img src="../assets/left-arrow.png" alt="" /></a>
      </div>
      <div class="pagination">
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">6</a>
      </div>
      <div class="left-arrow">
        <a href="#"><img src="../assets/right-arrow.png" alt="" /></a>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      login: {
        email: "",
        senha: "",
      },
    };
  },
};
</script>

<style scoped>
.center {
  display: flex;
  justify-content: space-around;
  text-align: center;
  margin-top: 100px;
  margin-bottom: 100px;
}

.pagination {
  display: inline-block;
  font-size: 14px;
}

.pagination a {
  color: rgba(102, 112, 133, 1);
  float: left;
  padding: 10px 16px;
  text-decoration: none;
  transition: background-color 0.3s;
  border: 0px solid #ddd;
  border-radius: 100%;
  margin: 0 4px;
}

.pagination a.active {
  background-color: #f9dc5c;
  color: rgba(102, 112, 133, 1);
  border: 0px solid #f9dc5c;
}

.pagination a:hover:not(.active) {
  background-color: #f5f5f5;
  padding: 11px 16px;
}
.left-arrow img {
  width: 24px;
}

.left-arrow img::hover {
  background-color: transparent;
}
</style>
