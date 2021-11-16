<template>

  <div class="form-group col-md-6" style="padding-left: 0px;">
    <!--label class="text-reader">
      Read Files
      <input type="file" @change="loadTextFromFile">
    </label-->


      <input
        class="text-reader"
        type="file"
        style="display: none"
        ref="fileInput"
        accept=".txt, .csv"
        @change="loadTextFromFile"/>

    <button type="button" class="btn btn-secondary btn-sm mr-1" @click="onPickFile">Load File...</button>
    <button type="button" class="btn btn-secondary btn-sm"  @click="$emit('clearText')">Clear</button>
  </div>

</template>

<script>
export default {
  methods: {
    onPickFile () {
      this.$refs.fileInput.click()
    },

    loadTextFromFile(ev) {
      const file = ev.target.files[0];
      const reader = new FileReader();

      reader.onload = e => this.$emit("load", e.target.result);
      reader.readAsText(file);
      console.log("FILE: " + file);
    }
  }
};
</script>

<style>

</style>
