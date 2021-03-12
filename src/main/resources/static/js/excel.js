/**
 * @auther: hxl
 * @date: 2021/3/12 10:43
 *
 *
 */
var excel = (function () {


    //事件绑定
    var bindEvent = function () {

    }


    //数据初始化
    var initData = function () {

        //导入插件渲染
        function initImport () {
            var uploader = WebUploader.create({
                // 文件接收服务端。
                server: globalUrl + '/pc/meeting/input',
                // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
                resize: false,
                pick: {id: "#inputBtn"},
                auto: true,
                multiple: false,
                fileSingleSizeLimit: 50 * 1024 * 1024,
                duplicate: true,
            });

            uploader.on('beforeFileQueued', function (file) {
                console.log(file)
                if (file) {
                    let fileType = file.ext.toLowerCase();
                    if ("xlsx".indexOf(fileType) === -1) {
                        warmInfo(false, "不支持的文件格式");
                        return false;
                    }
                    if (file.size > 50 * 1024 * 1024) {
                        warmInfo(false, "请上传小于50M的文件");
                        return false;
                    }
                } else {
                    warmInfo(false, "请选择文件");
                    return false;
                }
                return true;
            });

            uploader.on('uploadBeforeSend', function (obj, data, headers) {
            });

            //进度条
            uploader.on('uploadProgress', function (file, percentage) {
            });

            uploader.on('uploadSuccess', function (file, res) {
                console.log(res)
                if (res.code === 0) {
                    let data = res.data;
                    debugger;
                    var successDatas = data.pcCreditorExportSuccess;
                    var failDatas = data.pcCreditorExportFails;
                    var count = 0;
                    //成功的数据需要比对下是否在页面已存在
                    if(successDatas && successDatas.length>0){
                        $.each(successDatas,function () {
                            var index =  otherselmeetingUsers.findIndex(e => e.userTel === this.phone);
                            if(index>-1){
                                this.errorReason = "参会旁听人列表已存在该数据！";
                                failDatas.push(this);
                            }else {
                                index =  selmeetingUsers.findIndex(e => e.userTel === this.phone);
                                if(index>-1){
                                    this.errorReason = "参会债权人列表已存在该数据！";
                                    failDatas.push(this);
                                }else {
                                    var meetinguser = {};
                                    meetinguser["userId"] = this.phone;
                                    meetinguser["zqrId"] = this.phone;
                                    meetinguser["userName"] = this.realName;
                                    meetinguser["userTel"] = this.phone;
                                    meetinguser["userType"] = 1;
                                    meetinguser["debtAmount"] = 0;
                                    count ++;
                                    otherselmeetingUsers.push(meetinguser);
                                }
                            }

                        });

                        showmothermeetingUsers();
                    }

                    if(failDatas && failDatas.length>0){
                        importFailMsg(count,failDatas);
                        $('#importTipModal').modal('show');
                    }


                } else {

                }
            });

            uploader.on('uploadError', function (file) {
            });

            uploader.on('error', function (type) {
                if (type == "Q_TYPE_DENIED") {
                    errorInfo("不支持的文件格式");
                } else if (type == "F_EXCEED_SIZE") {
                    errorInfo("文件大小不能超过50M");
                } else {
                    errorInfo("上传出错,请检查后重新上传或联系管理员,错误代码:" + type);
                }
            });
            uploader.on('uploadComplete', function (file) {

            });
        }

    }


    return {
        init: function () {
            initData();
            bindEvent();
        }
    }
})();

$(function () {
    excel.init()
})