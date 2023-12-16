import { createApp } from 'vue'
import store from '@/scripts/store'
import router from '@/scripts/router'
import App from './App.vue'
import CKEditor from '@ckeditor/ckeditor5-vue'




createApp(App).use(store).use(router).use(CKEditor).mount('#app');

