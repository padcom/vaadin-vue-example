import { defineCustomElement } from 'vue'
import HelloWorld from './HelloWorld.ce.vue'

customElements.define('hello-world', defineCustomElement(HelloWorld))

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
