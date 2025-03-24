<template>
<v-app 
        app 
        id="app"
        dark>
      <v-card color="black" width="100%">
        <v-row>
          <v-col>
        <v-img id="main_image"
          :src="require('../assets/main image.png')"
        />
          </v-col>
        </v-row>
        <v-row>
 <v-card color="black" class="card_tabs">
 <v-card dark>
    <v-tabs 
      v-model="currentItem"
      align-tabs="title"
      color="#D3CDBD"
      background-color="black"
      
      sticky
    >
      <v-tab v-model="currentItem"
            v-for="tabs_item in tabs_items"
            @click="currentItem=tabs_item.id - 1"
            :key="tabs_item"
            :value="tabs_item"
            > {{tabs_item.name }}
      </v-tab>
    </v-tabs>
 </v-card>
    <v-tabs-window>
      <v-tabs-window-item v-model="currentItem"
                          v-for="tabs_item in tabs_items"
                          :key="tabs_item"
                          :value="tabs_item"> 
        <v-divider class="border-opacity-100 mr-9 ml-9 mt-3" color="#D3CDBD" v-if="((tabs_item.id != 0 & currentItem == tabs_item.id) | (tabs_item.id != 0 & currentItem == 0)) & !check_empty_category(tabs_item)"></v-divider>
        <span class="tab_dish_title center" v-if="((tabs_item.id != 0  & currentItem == tabs_item.id) | (tabs_item.id != 0 & currentItem == 0)) & !check_empty_category(tabs_item)">{{tabs_item.name}}</span>
        <v-divider class="border-opacity-100 mr-9 ml-9" color="#D3CDBD" v-if="((tabs_item.id != 0 & currentItem == tabs_item.id) | (tabs_item.id != 0 & currentItem == 0)) & !check_empty_category(tabs_item)"></v-divider>
        <v-container fluid v-if="((tabs_item.id != 0 & currentItem == tabs_item.id) | (tabs_item.id != 0 & currentItem == 0 )) & !check_empty_category(tabs_item)">
          <v-row>
            <v-col 
              v-for="item in items"
              :key="item"
              md="4"
            >
              <v-card color="black" id="dish" v-if="(tabs_item.id == item.category_id) | (currentItem == 0 & tabs_item.id == item.category_id)">
                <v-container>
                <v-row>
                <v-img id="dish_image" 
                  :src="require('../assets/' + item.image + '.png')"
                />
                </v-row>
                <v-row>
                  <v-col>
                    <v-row>
                    <span class="dish_name">{{item.name}}</span>
                    </v-row>
                    <v-row>
                      <!-- <v-span class="dish_price" v-if="item.count > 1">{{ item.price * item.count }} ₽</v-span> -->
                      <span class="dish_price">{{ item.price }} ₽</span>

                    </v-row>
                  </v-col>
                  <v-col > 
                    <!-- v-if="authorised" -->
                    <v-btn v-if="item.add & item.count == 0 & !authorised"
                        rounded="xl"
                        @click="login_notification_dialog = true"
                        class="add text-none text-subtitle"
                        outlined
                        target="_blank"
                        variant="flat"
                        color="#D3CDBD"
                        size="small"
                        density="compact"
                      >Добавить</v-btn>
                      <v-dialog v-model="login_notification_dialog" 
                                width="auto"
        >
          <v-card 
            color="black"
            dark
            width="500"
          ><v-card color="black" width="100%" height="100%" class="card_support">
             <v-card-text>
            <v-card color="black" dark >
              <v-btn 
                          class=" text-none text-subtitle mx-auto ma-2 ml-3 mr-3"
                          text
                          rounded="2"
                          width="80%"
                          target="_blank"
                          variant="flat"
                          color="#D3CDBD"
                          large
                          density="compact"
                          >Уведомление!</v-btn>
                      
                          <v-btn
                              @click="login_notification_dialog = false"
                              class=" text-none text-subtitle mx-auto ma-2"
                              light 
                              rounded="2"
                              icon
                              target="_blank"
                              variant="flat"
                              color="#D3CDBD"
                              small
                              density="compact"
                            ><v-img color="#D3CDBD" 
                                  class="ma-2" 
                                  src="../assets/close.svg"/></v-btn>

                  </v-card>
                  <v-card color="black" class="text-subtitle mx-auto ma-2 ml-3 mr-3">
                    <v-card-subtitle>Для оформления заказа необходимо авторизоваться.</v-card-subtitle>
                  </v-card>
             </v-card-text>
             </v-card>
          </v-card>
                      </v-dialog>
                      <v-btn v-if="item.add & item.count == 0 & authorised"
                        rounded="xl"
                        @click="item.count++, item.add=!item.add"
                        class="add text-none text-subtitle"
                        outlined
                        target="_blank"
                        variant="flat"
                        color="#D3CDBD"
                        size="small"
                        density="compact"
                      >Добавить</v-btn>
                      <!-- <v-btn icon v-model="dialog" @click="item.isActive = true">{{item.isActive}}</v-btn>   -->
                      
                    <v-btn v-if="!item.add & item.count >= 0"
                          rounded="l"
                          class="plus_minus text-none text-subtitle"
                          
                          variant="flat"
                          color="#D3CDBD"
                          size="small"
                          density="compact"
                        >
                        <v-btn v-if="item.count-1 >= 0"
                               icon 
                               target="_blank"
                               @click="item.count--"
                               >
                         <template>
                              <span color="#D3CDBD">–</span>
                          </template>
                        </v-btn>
                        <v-btn v-if="item.count-1 < 0"
                               icon 
                               target="_blank"
                               disabled
                               @click="item.add=!item.add"
                               >
                         <template>
                              <span color="#D3CDBD">–</span>
                          </template>
                        </v-btn>
                        {{item.count}}
                        <v-btn v-if="item.count >= 0 & item.count < item.stock_quantity"
                               icon 
                               target="_blank"
                               @click="item.count++"
                               >
                        <template>
                          <span color="#D3CDBD">+</span>
                        </template>
                        </v-btn>
                        <v-btn v-if="item.count >= item.stock_quantity"
                               icon 
                               disabled
                               target="_blank"
                               >
                        <template>
                          <span color="#D3CDBD">+</span>
                        </template>
                        </v-btn>
                        </v-btn>
                  </v-col>
                </v-row>
                </v-container>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
        <v-containr v-if="(tabs_item.id != 0 & currentItem == tabs_item.id) & check_empty_category(tabs_item)">
          <v-row>
          <v-col>
        <v-img id="main_image"
          :src="require('../assets/main image.png')"
        />
          </v-col>
        </v-row>
        </v-containr>
      </v-tabs-window-item>
    </v-tabs-window>
  </v-card>
  </v-row>
    </v-card>
</v-app>

</template>

<script>
  // var add_item = true;
  export default {
    name: 'Main_',
    data () {
      return {
        tab: null,
        currentItem: 0,
        tabs_items: [
          {id: 0, name: 'Все'}, 
          {id: 1, name: 'Салаты'}, 
          {id: 2, name: 'Закуски'}, 
          {id: 3, name: 'Горячие закуски'}, 
          {id: 4, name: 'Супы'}, 
          {id: 5, name: 'Лапша и рис'}, 
          {id: 6, name: 'Сашими'}, 
          {id: 7, name: 'Роллы'}, 
          {id: 8, name: 'Блюда на гриле'}, 
          {id: 9, name: 'Горячие блюда'}, 
          {id: 10, name: 'Десерты'}, 
          {id: 11, name: 'Напитки'}
        ],
        items: [
          {
          isActive: false,
          category_id: 3,
          image: "hot_snack1",
          name: 'Пирожок с томленой говяжей щекой',
          description: '',
          price: 600,
          count: 0,
          stock_quantity: 20,
          add: true,
        },
        {
          category_id: 3,
          image: "hot_snack2",
          name: 'Рыбный пирог с соусом из одуванчиков',
          description: '',
          price: 1300,
          count: 0,
          stock_quantity: 30,
          add: true,
        },
        {
          category_id: 3,
          image: "hot_snack3",
          name: 'Хуужууры',
          description: '',
          price: 700,
          count: 0,
          stock_quantity: 7,
          add: true,
        },
         {
          category_id: 1,
          image: "salad1",
          name: 'Зеленый салат с хвойными побегами',
          description: '',
          price: 1000,
          count: 0,
          stock_quantity: 23,
          add: true,
        },
        {
          category_id: 1,
          image: "salad2",
          name: 'Винегрет с мороженым из печеной капусты',
          description: '',
          price: 900,
          count: 0,
          stock_quantity: 15,
          add: true,
        },
        {
          category_id: 1,
          image: "salad3",
          name: 'Салат с олениной, брусникой и чипсами из топинамбура',
          description: '',
          price: 1500,
          count: 0,
          stock_quantity: 13,
          add: true,
        },
        {
          category_id: 2,
          image: "snack1",
          name: 'Грузди с луком и сметаной',
          description: '',
          price: 900,
          count: 0,
          stock_quantity: 23,
          add: true,
        },
        {
          category_id: 2,
          image: "snack2",
          name: 'Намазки из сибирских рыб',
          description: '',
          price: 2600,
          count: 0,
          stock_quantity: 25,
          add: true,
        },
        {
          category_id: 2,
          image: "snack3",
          name: 'Намазка из печеного сельдерея',
          description: '',
          price: 800,
          count: 0,
          stock_quantity: 13,
          add: true,
        },
        {
          category_id: 2,
          image: "snack4",
          name: 'Паштет из утки',
          description: '',
          price: 900,
          count: 0,
          stock_quantity: 13,
          add: true,
        },
        ],
        authorised: false,
        login_notification_dialog: false,
      }
  },
  methods: {
    check_empty_category(category){
        for (let x of this.items) {
             if (x.category_id == category.id){
              return false;
             }
        }
        return true;
        },
  }
  }
</script>

<style lang="scss">
.app {
  font-family: "Lexend Exa", sans-serif;
  // transition: background-color 0.3s ease;
  background: #000 url("../assets/wallpaper.png") no-repeat fixed left bottom;
}
.app.light-theme {
    background-color: #D3CDBD;
    color: #000;
}
.app.dark-theme {
    background-color: #000;
    color: #D3CDBD;
}
.card_tabs{
  background-color: #000;
  color: #D3CDBD;
  width: 80%;
  text-align: center;
  position: center;
  margin-left: 10%;
  margin-top: 2px;
  margin-bottom: 1%;
}
.tabs {
  // border-radius: 10px;
  color:#000;
  margin-top: 5px;
}
.main_image {
    width: 100%;
    height: 100%;
    position: relative;
    background-size: contain;
}
.dish {
  background-color: #000;
  color: #D3CDBD;
  margin-left: 10%;
  margin-top: 10%;
  margin-bottom: 10%;
  margin-right: 10%;
   box-shadow: 0 0 10px #444;
}
.dish_image {
    width: 100%;
    height: 100%;
    position: relative;
    background-size: contain;
}
.dish_name {
  font-family: "Lexend Exa", sans-serif;
  color: #D3CDBD;
  text-align: left;
  margin-left: 10%;
  margin-top: 1%;
  margin-bottom: 1%;
  margin-right: 1%;
}
.dish_price {
  font-family: "Lexend Exa", sans-serif;
  color: #D3CDBD;
  margin-left: 10%;
  margin-top: 1%;
  margin-bottom: 1%;
  margin-right: 1%;
}
.add {
  font-family: "Lexend Exa", sans-serif;
  text-align: center;
  display: inline-flex;
  flex-grow: 2;
  line-height: 100%;
  // border-radius: 15px;
  align-self: center;
  border-color: #D3CDBD;
}
.plus_minus {
  font-family: "Lexend Exa", sans-serif;
  text-align: center;
  color: #D3CDBD;
  width: 40%;
}
.tab_dish_title {
  font-family: "Lexend Exa", sans-serif;
  text-align: center;
  text-transform: uppercase;
  color: #D3CDBD;
}
.fixed-tabs-bar .v-tabs__bar {
  position: -webkit-sticky;
  position: sticky;
  top: 4rem;
  z-index: 2;
}
</style>
