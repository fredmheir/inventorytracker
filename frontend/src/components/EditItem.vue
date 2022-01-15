<template>
    <div v-if="currentItem">
        <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" required name="Name" v-model="currentItem.name">
            </div>
            <div class="mb-3">
                <label for="qty" class="form-label">Qty</label>
                <input type="number" class="form-control" id="qty" required name="qty" v-model="currentItem.qty">
            </div>
            <div class="mb-3">
                <label for="cost" class="form-label">Cost</label>
                <input type="number" class="form-control" id="cost" required name="cost" v-model="currentItem.cost">
            </div>
            <div class="mb-3">
                <button @click="updateItem" class="btn btn-primary me-3">Update</button>
                <button @click="deleteItem" class="btn btn-danger">Delete</button>
            </div>
             <div class="alert alert-success" role="alert" v-if="message">
                {{message}}
            </div>
    </div>
</template>

<script>
import ItemDataService from '../services/ItemDataService'

export default {
    name: 'edit-item',
    data() {
        return {
            currentItem: null,
            message: ''
        }
    },
    methods: {
        getItem(id) {
            ItemDataService.get(id)
                .then(response => {
                    this.currentItem = response.data
                })
                .catch(e => {
                    alert(e)
                })
        },
        updateItem() {
            ItemDataService.update(this.currentItem.id, this.currentItem)
                .then(() => {
                    this.message = 'The item was updated successfully!'
                })
                .catch(e => {
                    alert(e)
                })
        },
        deleteItem() {
            ItemDataService.delete(this.currentItem.id)
                .then(() => {
                    this.$router.push({name: 'items'})
                })
                .catch(e => {
                    alert(e)
                })
        }
    },
    mounted() {
        this.getItem(this.$route.params.id)
    }
}
</script>