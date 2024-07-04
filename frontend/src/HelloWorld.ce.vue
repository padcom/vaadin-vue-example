<template>
  <h1>Hello, {{ name }}! from a Vue.js custom element!!!!</h1>
  <button @click="triggerBackendEvent">Click me to trigger backend event</button>
  <button @click="formatHelloMessage">Format hello message</button>
  <button @click="formatHelloObject">Format hello object</button>
  <p>value: {{ value }}</p>
  <p><pre><code>{{ result }}</code></pre></p>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { useHostEl } from './use-host-el'

defineProps({
  name: { type: String, default: 'John' },
})

const { hostEl } = useHostEl()

function triggerBackendEvent() {
  hostEl.value?.dispatchEvent(new CustomEvent('test-event', { detail: { someValue: Math.random() } }))
}

const result = ref('')

async function formatHelloMessage() {
  // @ts-ignore The $server variable has no types
  result.value = await hostEl.value?.$server.formatHelloMessage('John')
}

async function formatHelloObject() {
  // @ts-ignore The $server variable has no types
  result.value = await hostEl.value?.$server.formatHelloObject('John')
}

const value = ref('Default value')

defineExpose({ value })
</script>

<style>
h1 {
  color: red;
}
</style>
