import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            alias: '/items',
            name: 'items',
            component: () => import('./components/ItemList')
        },
        {
            path: '/items/:id',
            name: 'edit-item',
            component: () => import('./components/EditItem')
        },
        {
            path: '/add',
            name: 'add-item',
            component: () => import('./components/AddItem')
        }
    ]
})