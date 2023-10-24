import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

import { UserConfigFn } from 'vite'
import { overrideVaadinConfig } from './vite.generated'

const customConfig: UserConfigFn = (env) => ({
  plugins: [
    vue()
  ],
})

export default overrideVaadinConfig(customConfig)
