<template>
  <h1>Hello, {{ name }}! from a Vue.js custom element!!!</h1>
  <button @click="testme">Click me to do some action!</button>
</template>

<script lang="ts" setup>
import { useHostEl } from './use-host-el'

defineProps({
  name: { type: String, default: 'John' },
})

const { hostEl } = useHostEl()

function testme() {
  hostEl.value?.dispatchEvent(new CustomEvent('test-event'))
}
</script>
