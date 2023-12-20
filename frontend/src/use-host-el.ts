import { ref, onMounted, getCurrentInstance } from 'vue'

/**
 * Use the element that was used to mount this component. Works only for web components!
 */
export function useHostEl() {
  const hostEl = ref<Node>()

  onMounted(() => {
    const root = getCurrentInstance()?.root.vnode.el?.getRootNode()

    if (root instanceof ShadowRoot) {
      hostEl.value = root.host
    } else if (root instanceof HTMLElement) {
      throw new Error('The useHostEl() composable can only be used for custom elements with ShadowRoot enabled')
    }
  })

  return { hostEl }
}
