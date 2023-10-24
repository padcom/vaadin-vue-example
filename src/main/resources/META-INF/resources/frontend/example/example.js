import { defineCustomElement } from 'vue'

/**
 * Using the `.ce.vue` makes the `<style>` section automatically
 * scoped to that element. It's a feature of the new vue-loader.
 */
import ExampleElement from './Example.ce.vue'

/**
 * This is how you define a webcomponent based on a Vue.js component
 * definition. In our case we'll take the `ExampleElement` class and
 * register it under the tag name `example-element`.
 * The complementary Java class that allows instantiating this element
 * lives in Example.java.
 */
window.customElements.define('example-element', defineCustomElement(ExampleElement))
