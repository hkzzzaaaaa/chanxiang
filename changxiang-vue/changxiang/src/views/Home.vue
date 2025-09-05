<script setup lang="ts">
const rank=function(pagenum: number,item:number){
    let items=(pagenum-1)*9+item;
    return items;
}
</script>
<template>
        <div class="top-picture">
        <img src="../assets/placard.png" style="max-width: 1250px;">
    </div>
    <div class="main">
        <div class="main-1">
            <div class="main-1-rank">
                <strong style="font-size: 25px;">[畅想排行榜]</strong>
                <br></br>
                <el-text>根据作品好评、人气、互动等综合得分排行</el-text>
                <br></br>
                <br></br>
                <el-carousel height="450px" motion-blur>
                <el-carousel-item v-for="item in 3" :key="item">
                    <div class="rank" v-for="items in 3" key:items>
                        <div class="rank-book">
                            <div>{{rank(item,items)}}</div>
                            <div>
                                <div class="rank-book-text">小说名字</div>
                                <p class="rank-book-category">小说类型</p>
                            </div>
                            <img class="rank-book-img" src="" alt="">     
                        </div>
                        <div class="rank-book">
                            <div>{{rank(item,items)+3}}</div>
                            <div>
                                <div>小说名字</div>
                                <p class="rank-book-category">小说类型</p>
                            </div>
                            <img class="rank-book-img" src="" alt="">      
                        </div>
                        <div class="rank-book">
                            <div>{{rank(item,items)+6}}</div>
                            <div>
                                <div>小说名字</div>
                                <p class="rank-book-category">小说类型</p>
                            </div>
                            <img class="rank-book-img" src="" alt="">      
                        </div>
                    </div>
                </el-carousel-item>
                </el-carousel>
            </div>
            <div class="main-1-new" >
                <strong style="font-size: 25px;">[最新资讯]</strong>
                <br></br>
                <br></br>
                <br></br>
                <div class="main-1-news" v-for="item in 8" :key="item">
                    <RouterLink to="/" class="new-type">公告：{{ item }}</RouterLink>
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped>
.top-picture{
    height: 480px;
}
.main-1{
    width: 1250px;
    background-color: rgb(255, 255, 255);
    display: flex;
    justify-content: space-between;
}
.main-1-rank{
    flex: 3;
}
.main-1-new{
    flex: 1;
    max-height: 500px;
}
.rank{
    display: flex;
    gap: 0px 140px;
}
.rank-book{
    display: flex;
    width: 200px;
    height: 150px;
    justify-content: space-around;
}
.rank-book-category{
    font-size: 10px;
}
.rank-book-img{
    width: 66px;
    height: 88px;
}
.rank-book-text{
    max-width: 116px;
    max-height: 50px;
    line-clamp: 2; 
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2; 
    overflow: hidden;
    text-overflow: ellipsis;
}
.main-1-news{
    display: flex;
    height: 60px;
    width: 300px;
}
.new-type{
    font-size: 15px;
    text-decoration: none;
    color: black;
    max-width: 300px;
    overflow: hidden;
    text-overflow: ellipsis;
}
</style>