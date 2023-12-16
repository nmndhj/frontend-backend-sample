<template>
  <div>

    <div class="card-body">


      <div class="mb-3 mt-3">
        <label for="bno" class="form-label">bno</label>
        <input type="text" class="form-control" id="id" name="id"
               v-model="form.id" readonly>
      </div>
      <div class="mb-3">
        <label for="title" class="form-label">title</label>
        <input type="text" class="form-control" id="title" name="title"
               v-model="form.title" readonly>
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">content</label>
        <ckeditor :editor="editor" v-model="form.content" :disabled="true" :config="editorConfig"></ckeditor>
      </div>
      <div class="mb-3">
        <label for="writer" class="form-label">writer</label>
        <input type="text" class="form-control" writer="title" name="writer"
               v-model="form.writer" readonly>

      </div>

      <button class="btn btn-outline-warning w-33" @click="fnUpdate()">수정</button>
      <button class="btn btn-outline-warning w-33" @click="fnDelete()">삭제</button>
      <button class="btn btn-outline-warning w-100" @click="fnList">목록</button>


    </div>
  </div>
</template>

<script>


import axios from "axios";

import router from "@/scripts/router";
import ClassicEditor from '@ckeditor/ckeditor5-build-classic'


export default {
  name: "BoardDetail",
  components: {},
  data() {
    return {
      requestBody: this.$route.query,
      id: this.$route.query.id,
      form: {
        id: "",
        title: "",
        content: "",
        writer: ""
      },
      editor: ClassicEditor,
      editorData: '<p>Content of the editor.</p>',
      editorConfig: {}
    }

  },

  mounted() {
    this.fnGetView();
  },
  methods: {
    fnGetView() {
      axios.get(`/api/board/` + this.id, {
        params: this.requestBody
      }).then(({data}) => {
        this.form.id = data.id;
        this.form.title = data.title;
        this.form.content = data.content;
        this.form.writer = data.writer;
      }).catch((err) => {
        if (err.message.indexOf('Network Error') > -1) {
          alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
        }
      })

    },
    fnList() {
      delete this.requestBody.id
      router.push({
        path: '/board',
        query: this.requestBody
      })
    },
    fnDelete() {
      if (!confirm("삭제하시겠습니까?")) return

      axios.delete(`/api/board/` + this.id, {})
          .then(() => {
            alert('삭제되었습니다.')
            this.fnList();
          }).catch((err) => {
        console.log(err)
      })
    },
    fnUpdate() {
      router.push({
        path: '/boardWrite',
        query: this.requestBody
      })
    }
  }
}

</script>

<style scoped>

</style>