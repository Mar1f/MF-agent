import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import LoveMaster from '../views/LoveMaster.vue'
import SuperAgent from '../views/SuperAgent.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/love-master',
    name: 'LoveMaster',
    component: LoveMaster
  },
  {
    path: '/super-agent',
    name: 'SuperAgent',
    component: SuperAgent
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router 