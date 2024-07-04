import { defineCustomElement } from 'vue'
import HelloWorld from './HelloWorld.ce.vue'

class HelloWorldCE extends defineCustomElement(HelloWorld) {
  /**
   *  This defines how to expose a reactive property from a Vue component
   */
  get value() {
    // @ts-ignore Be advised that `this._instance` is internal Vue API
    // and subject to change but there is no other way to do it at the moment.
    // See https://github.com/vuejs/core/issues/5540 for more information
    return this._instance.exposed.value.value;
  }
  set value(value: string) {
    // @ts-ignore this._instance
    return this._instance.exposed.value.value = value;
  }

  /**
   * Similarly to the reactive property above this is how a function can be exposed
   */
  sayHello() {
    // @ts-ignore this._instance
    this._instance.exposed.sayHello()
  }
}

customElements.define('hello-world', HelloWorldCE)

// class HelloWorld extends HTMLElement {
//   #root = this.attachShadow({ mode: 'open' })
//   #name: number | null = null

//   constructor() {
//     super()

//     const header = document.createElement('h1')
//     header.innerHTML = 'Hello, world!'
//     this.#root.appendChild(header)
//   }

//   get name() {
//     return this.#name
//   }

//   set name(name: number | null) {
//     this.#name = name
//   }
// }

// customElements.define('hello-world', HelloWorld)
