import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createPersistedState } from 'pinia-plugin-persistedstate'
import App from './App.vue'
import router from './router'
const persist=createPersistedState();
const pinia=createPinia();
const app = createApp(App)
pinia.use(persist);
app.use(ElementPlus)
app.use(createPinia())
app.use(router)
app.use(pinia);
app.mount('#app')
