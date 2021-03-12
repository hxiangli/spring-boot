//package com.hlfc.springboot.excel.doimport;
//
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.event.AnalysisEventListener;
//import com.alibaba.fastjson.JSON;
//import com.yjcloud.asr.icp.sclient.dto.bom.InputCreditorResult;
//import com.yjcloud.asr.icp.sclient.dto.bom.PcCreditorExportDTO;
//import com.yjcloud.asr.icp.sclient.dubbo.IPcCreditorRSV;
//import com.yjcloud.asr.icp.sclient.utils.PhoneFormatCheckUtils;
//import com.zpaas.utils.StringUtil;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//public class PcCreditorDataListener extends AnalysisEventListener<PcCreditorExportDTO> {
//    /**
//     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
//     */
//    private static final int BATCH_COUNT = 1000;
//    List<PcCreditorExportDTO> list = new ArrayList<PcCreditorExportDTO>();
//    private IPcCreditorRSV iPcCreditorRSV;
//
//    private InputCreditorResult inputCreditorResult;
//    /**
//     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
//     *
//     */
//    public PcCreditorDataListener(IPcCreditorRSV iPcCreditorRSV, InputCreditorResult inputCreditorResult) {
//        this.iPcCreditorRSV = iPcCreditorRSV;
//        this.inputCreditorResult = inputCreditorResult;
//    }
//    /**
//     * 这个每一条数据解析都会来调用
//     *
//     * @param data
//     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
//     * @param context
//     */
//    @Override
//    public void invoke(PcCreditorExportDTO data, AnalysisContext context) {
//        log.info("解析到一条数据:{}", JSON.toJSONString(data));
//        list.add(data);
//        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//        if (list.size() >= BATCH_COUNT) {
//            saveData();
//            // 存储完成清理 list
//            list.clear();
//        }
//    }
//    /**
//     * 所有数据解析完成了 都会来调用
//     *
//     * @param context
//     */
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext context) {
//        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
//        saveData();
//        log.info("所有数据解析完成！");
//    }
//    /**
//     * 加上存储数据库
//     */
//    private void saveData() {
//        log.info("{}条数据，开始存储数据库！", list.size());
//
//        List<PcCreditorExportDTO> pcCreditorExportSuccess = new ArrayList<>();
//        List<PcCreditorExportDTO> pcCreditorExportFails = new ArrayList<>();
//        Map<String,String> phoneMap = new HashMap<>();
//        //处理格式错误数据
//        int rowIndex = 1;
//        for (PcCreditorExportDTO pcCreditorExportDTO : list) {
//            String name = pcCreditorExportDTO.getRealName();
//            String phone = pcCreditorExportDTO.getPhone();
//            pcCreditorExportDTO.setRowIndex(rowIndex ++);
//            if(StringUtil.isEmpty(name)){
//                pcCreditorExportDTO.setRealName("");
//                pcCreditorExportDTO.setErrorReason("姓名不能为空！");
//            }
//
//            if(StringUtil.isEmpty(phone)){
//                pcCreditorExportDTO.setPhone("");
//                pcCreditorExportDTO.setErrorReason("联系方式不能为空！");
//            }else {
//                if(!PhoneFormatCheckUtils.isNumeric(phone)){
//                    pcCreditorExportDTO.setErrorReason("联系方式格式有误！");
//                }
//            }
//
//
//            if(phoneMap.containsKey(phone)){
//                pcCreditorExportDTO.setErrorReason("重复导入！");
//            }
//
//            if(!StringUtil.isBlank(pcCreditorExportDTO.getErrorReason())){
//                pcCreditorExportFails.add(pcCreditorExportDTO);
//            }else {
//                pcCreditorExportSuccess.add(pcCreditorExportDTO);
//                phoneMap.put(phone, phone);
//            }
//            inputCreditorResult.setPcCreditorExportFails(pcCreditorExportFails);
//            inputCreditorResult.setPcCreditorExportSuccess(pcCreditorExportSuccess);
//
//        }
//
//
//
//        log.info("存储数据库成功！");
//    }
//}