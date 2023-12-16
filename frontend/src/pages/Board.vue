<template>
  <div class="board">
    <div class="container">
      <button class="w-50 btn btn-lg btn-primary" @click="write">글쓰기</button>
      <table class="table">
        <thead>
        <tr class="table-header">
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
        </tr>
        </thead>
        <tbody>
        <tr class="active-table-tab" v-for="(row,idx) in list" :key="idx">
          <td>{{ row.id }}</td>
          <td><a href="javascript:;" @click="detail(`${row.id}`)">{{ row.title }}</a></td>
          <td>{{ row.writer }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/scripts/router";

export default {
  name: "Board",
  data() {
    return {
      requestBody: {},
      list:{}
    }
  },
  mounted() {
    this.fnGetList()
  },
  methods: {
    fnGetList(){
      axios.get("/api/board/list").then(({data}) => {
        console.log(data);
        this.list = data;
      }).catch((err) => {
        if (err.message.indexOf('Network Error') > -1) {
          alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
        }
      })
    },
    detail(id) {   //셋업으로 불러오니까 오류남(id를 못읽음)..  애로우함수처럼 적어도 에러남..
      this.requestBody.id = id;
      router.push({
        path: '/boardDetail',
        query: this.requestBody
      })
    },
    write() {
      router.push('/boardWrite')
    },

  }
}
</script>

<style scoped>
</style>
