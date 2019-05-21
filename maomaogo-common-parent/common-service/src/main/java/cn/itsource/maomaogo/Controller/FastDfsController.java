package cn.itsource.maomaogo.Controller;

import cn.itsource.maomaogo.util.AjaxResult;
import cn.itsource.maomaogo.util.FastDfsApiOpr;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FastDfsController {


    /**
     * 文件上传 上传到 fastdfs文件系统中去，并返回fileId【也就是路径，保存到数据库中】
     * @param file
     * @return
     */
    @PostMapping("/file/upload")
    public AjaxResult uploadFile(@RequestParam(value = "file")MultipartFile file){
        try {
            //获取到文件后缀
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            //调用方法
            byte[] bytes;
            String fileId = FastDfsApiOpr.upload(file.getBytes(), extension);
            //返回 filedId 也就是 文件保存在 分布式文件系统中的 路径
            return AjaxResult.me().setSuccess(true).setData(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败!");
        }
    }


    /**
     * 删除分布式文件系统中的文件
     * @param fileId
     * @return
     */
    @GetMapping("/file/delete")
    public AjaxResult deleteFile(@RequestParam("fileId") String fileId){
        try {
            //fileId = /group1/M00/00/01/wKgBBVzhdv-ALosSAACNWUaTOwk027.jpg
            //拆分组名和其他 tempFile 获取到 group1/M00/00/01/wKgBBVzhdv-ALosSAACNWUaTOwk027.jpg
            String tempFile = fileId.substring(1);
            //获取到组名 group1
            String group = tempFile.substring(0, tempFile.indexOf("/"));
            //获取到 路径名+数据二级目录+文件名称.后缀名 M00/00/01/wKgBBVzhdv-ALosSAACNWUaTOwk027.jpg
            String name = tempFile.substring(tempFile.indexOf("/")+1);
            //调用 工具类 删除 文件【需要组名和文件名称】
            FastDfsApiOpr.delete(group,name);
            return AjaxResult.me().setData(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败!");
        }

    }

}
