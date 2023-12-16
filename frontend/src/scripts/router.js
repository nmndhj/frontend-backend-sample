import {createRouter, createWebHistory} from 'vue-router'
import Home from '@/pages/Home'
import Login from '@/pages/Login'
import Cart from '@/pages/Cart'
import Order from '@/pages/Order'
import Orders from '@/pages/Orders'
import Board from '@/pages/Board'
import BoardDetail from '@/pages/BoardDetail'
import BoardWrite from "@/pages/BoardWrite.vue";

const routes =[
	{path: '/', component:Home},
	{path:'/login', component:Login},
	{path:'/cart', component:Cart},
	{path:'/order', component:Order},
	{path:'/orders', component:Orders},
	{path: '/board', component: Board},
	{

		path: '/boardDetail',
		component: BoardDetail,
		props: true
	},
	{path:'/boardWrite', component: BoardWrite}

]

const router =createRouter({
	history: createWebHistory(),
	routes
})

export default router;