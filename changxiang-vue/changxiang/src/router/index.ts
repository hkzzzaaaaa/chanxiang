import { createRouter, createWebHistory } from 'vue-router'
import Main from '@/views/Main.vue'
import Home from '@/views/Home.vue'
import Login from '@/views/Login.vue'
import Stack from '@/views/Stack.vue'
import Author from '@/views/Author.vue'
import '@/assets/main.css'
import Work from '@/views/Work.vue'
import WorkManagement from '@/views/WorkManagement.vue'
import DataManagement from '@/views/DataManagement.vue'
import BookInfo from '@/views/Book-Info.vue'
import Create from '@/views/Create.vue'
import ChapterManagement from '@/views/ChapterManagement.vue'
import Write from '@/views/Write.vue'
import Content from '@/views/Content.vue'
import Read from '@/views/Read.vue'
import { useTokenStore } from '@/stores/token'
import Information from '@/views/Information.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
     {
      path: '/',      
      name: 'main',
      component: Main, 
      children:[
        {
        path: '', 
        name: 'home',
        component: Home,
        meta: { requiresAuth: false }
      },
      {
        path: 'stack',
        name: 'stack',
        component: Stack,
        meta: { requiresAuth: false }
      },
      {
        path: 'information',
        name: 'information',
        component: Information,
        meta: { requiresAuth: true }
      },
      {
        path: 'author',
        name: 'author',
        component: Author,
        meta: { requiresAuth: true },
        children:[{
            path: 'work',
            name: 'work',
            component: Work,
         },
         {
          path: 'workManagement',
          name: 'workManagement',
          component: WorkManagement
         },
         {
          path: 'dataManagement',
          name: 'dataManagement',
          component: DataManagement
         },
         {
          path: 'Book-Info/:issign',
          name: 'Book-Info',
          component: BookInfo
         },
          {path: 'Create',
          name: 'Create',
          component: Create
        },{
          path: 'ChapterManagement',
          name: 'ChapterManagement',
          component:ChapterManagement
        }  
        ]
      },
      {
        path: 'Content',
        name: 'Content',
        component: Content,
        meta: { requiresAuth: false } 
      }
      ]
    },
    {
        path:'/login',
        name: 'login',
        component: Login,
        meta: { requiresAuth: false } 
      },
      {
        path: '/write',
        name: 'write',
        component: Write,
        meta: { requiresAuth: true } 
      },
      {
        path: '/read',
        name: 'read',
        component: Read,
        meta: { requiresAuth: false } 
      }
  ],
})
router.beforeEach((to, from, next) => {
    const tokenStore = useTokenStore();
    if (to.meta.requiresAuth) {
        if (tokenStore.token) {
            next(); 
        } else {
            next({
                path: '/login',
                query: { redirect: to.fullPath } 
            });
        }
    } else {
        next();
    }
});
export default router
