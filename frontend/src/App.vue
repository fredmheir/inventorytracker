<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <router-link to="/" class="navbar-brand">Inventory Manager</router-link>
        <div class="collapse navbar-collapse">
          <div class="navbar-nav">
            <router-link to="/items" class="nav-link">Item List</router-link>
            <router-link to="/add" class="nav-link">Add New Item</router-link>
          </div>
        </div>
      </div>
    </nav>
    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>