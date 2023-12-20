import { defineCustomElement } from 'vue'
import HelloWorld from './HelloWorld.ce.vue'

customElements.define('hello-world', defineCustomElement(HelloWorld))

// customElements.define('hello-world', class extends HTMLElement {
//   #root = this.attachShadow({ mode: 'open' })

//   constructor() {
//     super()

//     const header = document.createElement('h1')
//     header.innerHTML = 'Hello, world!'
//     this.#root.appendChild(header)
//   }
// })
