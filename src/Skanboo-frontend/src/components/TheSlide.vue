<template>
  <div class="main">
    <div class="slider slider-for">
      <div class="categoria">
        <h3>1</h3>
      </div>
      <div>
        <h3>2</h3>
      </div>
      <div>
        <h3>3</h3>
      </div>
      <div>
        <h3>4</h3>
      </div>
      <div>
        <h3>5</h3>
      </div>
    </div>
    <div class="slider slider-nav">
      <div class="categoria">
        <img src="images/cat-1.png" alt="" />
        <h3>Eletrônicos</h3>
      </div>

      <div class="categoria">
        <img src="images/cat-2.png" alt="" />
        <h3>Casa</h3>
      </div>

      <div class="categoria">
        <img src="images/cat-3.png" alt="" />
        <h3>Beleza</h3>
      </div>

      <div class="categoria">
        <img src="images/cat-4.png" alt="" />
        <h3>Moda</h3>
      </div>

      <div class="categoria">
        <img src="images/cat-1.png" alt="" />
        <h3>Casa</h3>
      </div>

      <div class="categoria">
        <img src="images/cat-1.png" alt="" />
        <h3>Casa</h3>
      </div>

      <div class="categoria">
        <img src="images/cat-1.png" alt="" />
        <h3>Casa</h3>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TheHeader",
};
</script>

<style scoped>
.main {
  width: 700px;
  display: block;
  margin: 0 auto;
  margin-top: 50px;
  margin-bottom: 100px;
}
.slider {
  display: flex;
  justify-items: space-around;
}
.action {
  display: block;
  margin: 100px auto;
  width: 100%;
  text-align: center;
}
.action a {
  display: inline-block;
  padding: 5px 10px;
  background: #f30;
  color: #fff;
  text-decoration: none;
}
.action a:hover {
  background: #000;
}

.slider-for {
  display: none;
}

.categoria img {
  padding: 10px;
  width: 200px;
  height: 200px;
  max-width: 100%;
}

.categoria h3 {
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 24px;
  text-align: center;
  margin-left: -20px;
  letter-spacing: 0.05em;
  color: #9798b0;
}
</style>
