<template>
  <h1>Hello, {{ name }}! from a Vue.js custom element!!!!</h1>
  <button @click="testme">Click me to do some action!</button>

  <button @click="test2">Test me</button>
</template>

<script lang="ts" setup>
import { useHostEl } from './use-host-el'

defineProps({
  name: { type: String, default: 'John' },
})

const { hostEl } = useHostEl()

function testme() {
  hostEl.value?.dispatchEvent(new CustomEvent('test-event', { detail: { someValue: Math.random() } }))
}

function test2() {
  // @ts-ignore xxx
  console.log(this)
}
</script>

<style>
h1 {
  color: red;
}
</style>
