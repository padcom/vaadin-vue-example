import { UserConfigFn } from 'vite';
import { overrideVaadinConfig } from './vite.generated';

//import vue from '@vitejs/plugin-vue'

const customConfig: UserConfigFn = (env) => ({
  plugins: [
//    vue()
  ],
});

export default overrideVaadinConfig(customConfig);
