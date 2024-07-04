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

// Triggering events that can be handled in the backend.
// Please note that it needs to be a browser event, not a Vue event
// so `defineEmits` will not work here
function triggerBackendEvent() {
  hostEl.value?.dispatchEvent(new CustomEvent('test-event', { detail: { someValue: Math.random() } }))
}

// Example of how to call backend functions from frontend through the host.$server proxy
// See hello-world.ts/HelloWorldCE.value (get/set) for how to make it available to the browser
const result = ref('')

async function formatHelloMessage() {
  // @ts-ignore The $server variable has no types
  result.value = await hostEl.value?.$server.formatHelloMessage('John')
}

async function formatHelloObject() {
  // @ts-ignore The $server variable has no types
  result.value = await hostEl.value?.$server.formatHelloObject('John')
}

// Example how to expose a reactive property and a method
// See hello-world.ts/HelloWorldCE.sayHello for how to make it available to the browser
const value = ref('Default value')

defineExpose({
  value,
  sayHello() {
    alert('Hello, world!')
  },
})
</script>

<style>
h1 {
  color: red;
}
</style>
