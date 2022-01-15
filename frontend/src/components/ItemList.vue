<template>
    <div>
        <table class="table">
            <thead>
                <tr>
                <th scope="col">Name</th>
                <th scope="col">Qty</th>
                <th scope="col">Cost</th>
                <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody v-for="(item, index) in items" :key="index">
                <tr>
                    <td>{{item.name}}</td>
                    <td>{{item.qty}}</td>
                    <td>{{item.cost}}</td>
                    <td><a :href="'/items/' + item.id" class="btn btn-primary">Edit</a></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import ItemDataService from '../services/ItemDataService'

export default {
    name: 'items',
    data() {
        return {
            items: []
        }
    },
    methods: {
        retrieveItems() {
            ItemDataService.getAll()
                .then(response => {
                    this.items = response.data
                })
                .catch(e => {
                    alert(e)
                })
        }
    },
    mounted() {
        this.retrieveItems()
    }
}
</script>