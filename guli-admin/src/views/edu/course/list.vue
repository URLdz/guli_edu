<template>
  <div class="app-container">
    课程列表
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.title" placeholder="课程名" />
      </el-form-item>

    <el-form-item>
        <el-select
          v-model="courseQuery.status"
          clearable
          placeholder="是否发布"
        >
          <el-option :value="Normal" label="已发布" />
          <el-option :value="Draft" label="未发布"/>
        </el-select>
      </el-form-item>

      <el-button
        type="primary"
        icon="el-icon-search"
        @click="getCourseListPage()"
        >查询</el-button
      >
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="title" label="名称"  />

      <el-table-column label="状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === 'Normal' ? "已发布" : "未发布" }}
        </template>
      </el-table-column>

      <el-table-column prop="lessonNum" label="课时数" width="80"/>

      <el-table-column prop="gmtCreate" label="添加时间" width="160" />

      <el-table-column prop="viewCount" label="浏览数量" width="160" />

      <el-table-column label="操作" width="500" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/info/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >编辑课程基本信息</el-button
            >
          </router-link>
          <router-link :to="'/course/chapter/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >编辑课程大纲信息</el-button
            >
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.id)"
            >删除课程信息</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="getCourseListPage"
    />
  </div>
</template>
  
<script>
import course from "@/api/edu/course.js";

export default {
  //核心代码
  data() {
    return {
      //返回结果
      list: null,
      //条件参数
      page: 1, //当前页
      limit: 6, //每页记录数
      total: 0,//总记录数
      Normal:"Normal",
      Draft:"Draft",
      courseQuery: {
        status:''

      }, //条件封装对象
    };
  },
  created() {
    this.getCourseListPage();
  },
  methods: {
    //课程列表方法
    getCourseListPage(page = 1) {
      this.page = page;
      course
        .getCourseListPage(this.page, this.limit, this.courseQuery)
        .then((response) => {
            console.log(response); 
          this.list = response.data.rows;
          this.total = response.data.total;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    //根据id删除课程
    removeDataById(id) {
      this.$confirm("此操作将永久删除该课程记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        course.deleteCourseId(id).then((response) => {
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          this.getCourseListPage();
        });
      });
    },

    //清空的方法
    resetData() {
      //表单输入项数据清空
      this.courseQuery = {status:''};
      //恢复查询所有课程数据
      this.getCourseListPage();
    },

  },
};
</script>