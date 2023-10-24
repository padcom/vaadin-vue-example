<template>
  <h1>Hello, {{ name }} from Vue.js in webcomponent from Vaadin</h1>
</template>

<script setup>
defineProps({
  name: { type: String, default: 'Jane' },
})
</script>
