<template>
  <div>

    <div class="card-body">

<!--      <form method="post">    이거때문에 두번 서브밋되서 에러... -->
        <div class="mb-3 mt-3">
          <label for="bno" class="form-label">bno</label>
          <input type="text" class="form-control" id="id" name="id"
                 v-model="form.id" readonly>
        </div>
        <div class="mb-3">
          <label for="title" class="form-label">title</label>
          <input type="text" class="form-control" id="title" name="title"
                 v-model="form.title">
        </div>
        <div class="mb-3">
          <label for="content" class="form-label">content</label>
          <ckeditor :editor="editor" v-model="form.content" :config="editorConfig"></ckeditor>
        </div>
        <div class="mb-3">
          <label for="writer" class="form-label">writer</label>
          <input type="text" class="form-control" id="writer" name="writer"
                 v-model="form.writer" readonly>
        </div>
        <button class="btn btn-outline-primary w-33" @click="fnList(form.id)" >목록</button>
        <button class="btn btn-outline-warning w-33" @click="fnSave()">작성완료</button>


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
  this.fnGetView()

  },
  methods: {
    fnGetView() {
      if (this.id !== undefined) {
        axios.get('api/board/' + this.id, {
          params: this.requestBody
        }).then((res) => {
          this.form.title = res.data.title
          this.form.writer = res.data.writer
          this.form.content = res.data.content
          this.form.id = res.data.id
        }).catch((err) => {
          console.log(err)
        })
      }
    },
    fnList() {
      delete this.requestBody.id
      router.push({
        path: '/Board',
        query: this.requestBody
      })
    },
    fnView(id){
      this.requestBody.id = id;
      router.push({
        path:'/BoardDetail',
        query:this.requestBody
      })
    },
    fnSave() {
      if (this.id === undefined) { // create
        const args = JSON.parse(JSON.stringify(this.form));

        axios.post(`/api/board/`, args).then((res) => {
          alert('완료하였습니다.');
          this.fnView(res.data.id)
        }).catch((err) => {
          console.log(err);
        })
      } else {  // update
        const args = JSON.parse(JSON.stringify(this.form));
        axios.patch(`/api/board/`, args)
            .then(({data}) => {
              alert('글이 저장되었습니다.')
              this.fnView(data.id)
            }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
      }

    }

  }
}

</script>

<style scoped>

</style>