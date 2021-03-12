//package com.hlfc.springboot.controller;
//
//import org.apache.velocity.shaded.commons.io.FilenameUtils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * @Auther: hxl
// * @Date: 2021/3/12 10:39
// */
//public class ExcelImportController {
//
//
//    @PostMapping("/input")
//    @ResponseBody
//    public ServerResponse<InputCreditorResult> inputCreditor(MultipartFile file){
//        if(file == null || file.isEmpty()){
//            return ServerResponse.illegalArguments("文件不能为空");
//        }
//        String originalFilename = file.getOriginalFilename();
//        String extension = FilenameUtils.getExtension(originalFilename);
//        if(StringUtils.isEmpty(extension)){
//            return ServerResponse.illegalArguments("不支持的文件类型!");
//        }else{
//            if(!"xlsx".equalsIgnoreCase(extension) && !"xls".equalsIgnoreCase(extension)){
//                return ServerResponse.illegalArguments("不支持的文件类型!");
//            }
//        }
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        ExcelReader excelReader = null;
//        InputCreditorResult result = new InputCreditorResult();
//        try {
//            excelReader = EasyExcel.read(file.getInputStream(),PcCreditorExportDTO.class,new PcCreditorDataListener(iPcCreditorRSV,result)).build();
//
//            ReadSheet readSheet = EasyExcel.readSheet(0).build();
//            excelReader.read(readSheet);
//            log.info("input result:{}",result);
//        } catch (Exception e) {
//            log.error("导入异常",e);
//            return ServerResponse.ok("导入失败:"+e.getMessage());
//        } finally {
//            if (excelReader != null) {
//                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//                excelReader.finish();
//            }
//        }
//        return ServerResponse.ok("上传成功",result);
//    }
//}
