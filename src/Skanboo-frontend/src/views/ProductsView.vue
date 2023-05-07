<template>
  <section>
    <TheProducts />
    <ThePagination />
  </section>
</template>

<script>
import TheProducts from "@/components/TheProducts.vue";
import ThePagination from "@/components/ThePagination.vue";
//import TheSlide from "@/components/TheSlide.vue";

export default {
  components: {
    TheProducts,
    ThePagination,
    //TheSlide,
  },
};
</script>
