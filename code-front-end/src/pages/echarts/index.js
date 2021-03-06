import React from 'react'
import {Card} from 'antd'
import ReactEcharts from 'echarts-for-react';
import echartTheme from './echartTheme'
import echarts from 'echarts/lib/echarts'
import 'echarts/lib/chart/line'
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';
import 'echarts/lib/component/legend';
import 'echarts/lib/component/markPoint';
import axios from '../../axios/index'
export default class UserDetail extends React.Component {

    state = {};

    componentWillMount(){
        echarts.registerTheme('theme',echartTheme); 
    }

    componentDidMount(){
        let userId = this.props.match.params.userId;
        if(userId){
            this.getDetailInfo(userId);
            this.getUserFoodHistory(userId);
            this.getUserSportHistory(userId);
        }
    }

    getDetailInfo = (userId)=>{
        axios.ajax({
            url:'/relationship/get',
            method:'get',
            data:{
                params:{
                    id: userId
                }
            }
        }).then((res)=>{
            if(res.code == 0){
                this.setState({
                    userIndexes:res.result.userIndexes,
                    foodEnergies:res.result.foodEnergies,
                    sportEnergies:res.result.sportEnergies
                });
            }
        })
    };

    getUserFoodHistory = (userId) => {
        axios.ajax({
            url:'/user_food_history/get',
            method:'get',
            data:{
                params:{
                    userId: userId
                }
            }
        }).then((res)=>{
            if(res.code == 0){
            this.setState({
                userFoodHistory:res.result
            });
        }
    })
    };

    getUserSportHistory = (userId) => {
        axios.ajax({
            url:'/user_sport_history/get',
            method:'get',
            data:{
                params:{
                    userId: userId
                }
            }
        }).then((res)=>{
            if(res.code == 0){
            this.setState({
                userSportHistory:res.result
            });
        }
    })
    };

    isNull(exp) {
        if (!exp[1] || typeof(exp[1]) == undefined)
        {
            return true;
        } else {
            return false;
        }

    }

    getfoodEnergiesOption(userFoodHistory) {
        let option = {
            title : {
                text : '???????????????',
            },
            tooltip : {
                trigger: 'item',
                formatter : function (params) {
                    var date = params.value[0];
                    var items = params.value[2];
                    var itemStr = '';
                    for(var item in items) {
                        itemStr += item + ': ' + items[item] + ' ??????' + '<br/>';
                    }
                    return '??????: ' + date + '<br/>'
                        + '????????????: ' + params.value[1] + ' ??????' + '<br/>'
                        + itemStr;
                }
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            dataZoom: {
                show: true,
                start : 70
            },
            grid: {
                y2: 80
            },
            xAxis : [
                {
                    type : 'time',
                    splitNumber:10
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLabel : {
                        formatter: function(value){
                            return value + " ??????";}
                    }
                },

            ],
            series : [
                {
                    name: 'series1',
                    type: 'line',
                    showAllSymbol: true,
                    symbolSize: function (value){
                        return Math.round(value[1] / 100);
                    },
                    data: (function () {
                        var d = [];
                        var len = userFoodHistory.length;
                        for(let i = 0; i < len; i++) {

                            let itemArr = [];

                            itemArr.push(userFoodHistory[i].collectDate);
                            itemArr.push(userFoodHistory[i].sumFoodEnergy);

                            let item = userFoodHistory[i].userFoodHistories;
                            let itemLength = item.length;

                            let itemobj = {};
                            for (let j = 0; j < itemLength; j++) {
                                itemobj[item[j].food.name] = parseInt(item[j].foodQuantity / 500 * item[j].food.foodEnergy);
                            }
                            itemArr.push(itemobj);
                            d.push(itemArr);
                        }

                        return d;
                    })()
                }
            ]
        };


        return option;
    }

    getsportEnergiesOption(sportEnergies) {
    let option = {
        title : {
            text : '???????????????',
        },
        tooltip : {
            trigger: 'item',
            formatter : function (params) {
                var date = params.value[0];
                var items = params.value[2];
                var itemStr = '';
                for(var item in items) {
                    itemStr += item + ': ' + items[item] + ' ??????' + '<br/>';
                }
                return '??????: ' + date + '<br/>'
                    + '????????????: ' + params.value[1] + ' ??????' + '<br/>'
                    + itemStr;
            }
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        dataZoom: {
            show: true,
            start : 70
        },
        grid: {
            y2: 80
        },
        xAxis : [
            {
                type : 'time',
                splitNumber : 10
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: function(value){
                        return value + " ??????";}
                }
            },

        ],
        series : [
            {
                name: 'series1',
                type: 'line',
                showAllSymbol: true,
                symbolSize: function (value){
                    return Math.round(value[1] / 150);
                },
                data: (function () {
                    var d = [];
                    var len = sportEnergies.length;
                    for(let i = 0; i < len; i++) {

                        let itemArr = [];

                        itemArr.push(sportEnergies[i].collectDate);
                        itemArr.push(sportEnergies[i].sumConsumeEnergy);

                        let item = sportEnergies[i].userSportHistories;
                        let itemLength = item.length;

                        let itemobj = {};
                        for (let j = 0; j < itemLength; j++) {
                            itemobj[item[j].sport.name] = item[j].sportTime * item[j].sport.consumeEnergy;
                        }
                        itemArr.push(itemobj);
                        d.push(itemArr);
                    }

                    return d;
                })()
            }
        ]
    };

    return option;
    }

    getOptionAll(userIndexes, foodEnergies, sportEnergies) {

        let option = {
            title: {
                text: '???????????????????????????????????????????????????'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend:{
                data:['????????????','???????????????','???????????????????????????', '????????????????????????']
            },
            xAxis: {
                data: this.isNull(foodEnergies)  ? [] : foodEnergies.map((item)=>{
                        return item.collectDate;
                    }
                )
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: '????????????',
                    type: 'line',
                    stack: '??????',
                    data: this.isNull(userIndexes) ? [] : userIndexes['1'].map((item)=>{
                            return item.indexContent;
                        }
                    ),
                    markPoint : {
                        data : [
                            {type : 'max', name: '?????????'},
                            {type : 'min', name: '?????????'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '?????????'}
                        ]
                    }
                },
                {
                    name: '???????????????',
                    type: 'line',
                    stack: '??????',
                    data: this.isNull(userIndexes) ? [] : userIndexes['2'].map((item)=>{
                    return item.indexContent;
                }
                ),
                    markPoint : {
                        data : [
                            {type : 'max', name: '?????????'},
                            {type : 'min', name: '?????????'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '?????????'}
                        ]
                    }
                },
                {
                    name: '???????????????????????????',
                    type: 'line',
                    stack: '??????',
                    data: this.isNull(foodEnergies) ? [] : foodEnergies.map((item)=>{
                        return item.energy;
                        }
                    ),
                    markPoint : {
                        data : [
                            {type : 'max', name: '?????????'},
                            {type : 'min', name: '?????????'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '?????????'}
                        ]
                    }
                },
                {
                    name: '????????????????????????',
                    type: 'line',
                    stack: '??????',
                    data: this.isNull(sportEnergies) ? [] : sportEnergies.map((item)=>{
                                return item.energy;
                            }
                    ),
                    markPoint : {
                        data : [
                            {type : 'max', name: '?????????'},
                            {type : 'min', name: '?????????'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '?????????'}
                        ]
                    }
                }
            ]
        };
        return option;
    }


    render() {
        const userIndexes = this.state.userIndexes || {};
        const foodEnergies = this.state.foodEnergies || {};
        const sportEnergies = this.state.sportEnergies || {};

        const userFoodHistory = this.state.userFoodHistory || {};
        const userSportHistory = this.state.userSportHistory || {};


    return (
            <div>
                <Card title="?????????????????????" style={{marginTop:10}}>
                    <ReactEcharts
                        option={this.getfoodEnergiesOption(userFoodHistory)}
                        theme="theme"
                        notMerge={true}
                        lazyUpdate={true}
                        style={{
                        height: 500
                    }}/>
                </Card>
                <Card title="?????????????????????" style={{marginTop:10}}>
                    <ReactEcharts
                        option={this.getsportEnergiesOption(userSportHistory)}
                        theme="theme"
                        notMerge={true}
                        lazyUpdate={true}
                        style={{
                        height: 500
                    }}/>
                </Card>
                <Card title="?????????????????????" style={{marginTop:10}}>
                    <ReactEcharts
                        option={this.getOptionAll(userIndexes, foodEnergies, sportEnergies)}
                        theme="theme"
                        notMerge={true}
                        lazyUpdate={true}
                        style={{
                            height: 500
                        }}/>
                </Card>
            </div>
        );
    }
}