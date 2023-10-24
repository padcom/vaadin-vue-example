<!--
  This is a very simple and quite frankly useless component.
  All it does is it displays either parsed `value` into `items`
  (if it is parse-able) or just the `value` in a hello-like message.
-->
<template>
  <ul v-if="items">
    <li v-for="item in items" :key="item.id">
      {{ item.id }} -> {{ item.name }}
    </li>
  </ul>
  <h1 v-else>Hello, {{ value }} from Vue.js in webcomponent from Vaadin</h1>
</template>

<script setup>
import { computed, watch } from 'vue'

/**
 * On Vaadin side we'll use `getElement().setProperty("name", "content")`
 * to change the value of `name` prop.
 */
const props = defineProps({
  value: { type: String, default: 'Jane' },
})

/**
 * This component emits `data-changed` event when the `props` change
 */
const emit = defineEmits(['data-changed'])

watch(computed(() => props.value), (newValue, oldValue) => {
  console.log('[FE] Value changed from', oldValue, 'to', newValue)

  // Emitting an event here results in dispatching a new instance of
  // `CustomEvent` which has the `detail` field set to the first parameter
  // after the event name. In our case it is the `newValue` parameter
  // to the `watch`
  emit('data-changed', newValue)
}, { immediate: false })

/**
 * Remember! Every value passed here will be a String first.
 * This is how webcomponents work. Vue does provide simple values
 * conversion for `Number` and `Boolean` but complex objects will need
 * to be serialized to `String` in Java (or whoever uses this component)
 * and deserialized here.
 *
 * This computed property tries to parse the `value` as JSON
 * and if it fails it returns a `false` to let the template know
 * what to display.
 */
const items = computed(() => {
  try {
    console.log('[FE] props.name:', props.value)
    return JSON.parse(props.value)
  } catch (e) {
    console.log('[FE] Unable to parse name:', e.message)
    return false
  }
})

/**
 * This is just a dummy function to show that we can call
 * functions on the custom element.
 */
function sayHello() {
  console.log('[FE] Hello, world! from Vue.js')
}

/**
 * This is how we make the `sayHello` function available
 * to others for calling
 */
defineExpose({ sayHello })
</script>

<style>
:host {
  /**
   * This is a convoluted way to provide default value for a CSS variable
   * and yet to allow whoever uses this component to override it.
   * If that'd be used directly in the styles, since there is a `shadowRoot`
   * it would not be passed through.
   * Original concept from Laravel, if memory serves...
   */

  --_text-color: var(--text-color, green);
}

h1 {
  color: var(--_text-color);
}
</style>
