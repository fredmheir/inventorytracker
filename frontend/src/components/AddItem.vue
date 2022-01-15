<template>
    <div>
        <div v-if="!submitted">
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" required name="name" v-model="item.name">
            </div>
            <div class="mb-3">
                <label for="qty" class="form-label">Qty</label>
                <input type="number" class="form-control" id="qty" required name="qty" v-model="item.qty">
            </div>
            <div class="mb-3">
                <label for="cost" class="form-label">Cost</label>
                <input type="number" class="form-control" id="cost" required name="cost" v-model="item.cost">
            </div>
            <div class="mb-3">
                <button @click="saveItem" class="btn btn-primary">Submit</button>
            </div>
        </div>
        <div v-else>
            <div class="alert alert-success" role="alert">
                Save item successfully!
            </div>
            <button @click="newItem" class="btn btn-primary">Add New Item</button>
        </div>
    </div>
</template>

<script>
import ItemDataService from '../services/ItemDataService'

export default {
    name: 'add-item',
    data() {
        return {
            item: {
                id: null,
                name: "",
                qty: "",
                cost: ""
            },
            submitted: false
        }
    },
    methods: {
        saveItem() {
            var data = {
                name: this.item.name,
                qty: this.item.qty,
                cost: this.item.cost,
            }
            ItemDataService.create(data)
                .then(response => {
                    this.item.id = response.data.id
                    this.submitted = true;
                })
                .catch( e => {
                    alert(e)
                })
        },
        newItem() {
            this.submitted = false
            this.item = {}
        }
    }
}
</script>