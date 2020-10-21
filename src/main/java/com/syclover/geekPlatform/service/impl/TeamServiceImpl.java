package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.dao.TeamMapper;
import com.syclover.geekPlatform.entity.Team;
import com.syclover.geekPlatform.entity.User;
import com.syclover.geekPlatform.service.TeamService;
import com.syclover.geekPlatform.util.CleanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author cueyu
 * @Date 2020/8/21
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamMapper teamMapper;

//  创建团队
    @Override
    public ResultT<Team> addTeam(Team team) {
        int result = teamMapper.addTeam(team);
        if (result == 0){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            Team data = teamMapper.getTeamByToken(team.getToken());
            String token = team.getToken();
            Team newTeam = CleanUtil.cleanTeam(data);
            newTeam.setToken(token);
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),newTeam);
        }
    }

//    根据队伍token来加入用户id
    @Override
    public ResultT<Team> addTeamate(int id, String token) {
        int result = teamMapper.addTeamate(id, token);
        if (result == 0){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),null);
        }
    }

//    根据id得到队伍对象
    @Override
    public ResultT<Team> getTeam(int id) {
        Team team = teamMapper.getTeamById(id);
        if (team == null){
            return new ResultT<>(ResponseCode.TEAM_NOT_FOUND.getCode(),ResponseCode.TEAM_NOT_FOUND.getMsg(),null);
        }else {
            team = CleanUtil.cleanTeam(team);
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),team);
        }
    }

    @Override
    public ResultT getTeamWithToken(int id) {
        Team team = teamMapper.getTeamById(id);
        if (team == null){
            return new ResultT<>(ResponseCode.TEAM_NOT_FOUND.getCode(),ResponseCode.TEAM_NOT_FOUND.getMsg(),null);
        }else {
            String token = team.getToken();
            team = CleanUtil.cleanTeam(team);
            team.setToken(token);
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),team);
        }
    }

    //    根据队名得到队伍对象
    @Override
    public ResultT<Team> getTeam(String name) {
        Team team = teamMapper.getTeamByName(name);
        if (team == null){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),team);
        }
    }

//    根据token得到队伍对象
    @Override
    public ResultT<Team> getTeamByToken(String token) {
        Team team = teamMapper.getTeamByToken(token);
        if (team == null){
            return new ResultT<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),null);
        }else {
            return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(),team);
        }
    }

//    得到所有队伍名字
    @Override
    public ResultT<List<String>> getAllName() {
        List<Team> teamList = teamMapper.getAllName();
        List<String> names = new ArrayList<String>();
        for (Team team : teamList){
            names.add(team.getName());
        }
        return new ResultT<List<String>>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),names);
    }

    @Override
    public ResultT<Team> checkUserInTeam(String token, int teamID, int userID) {

        // 参数验证
        if (token == null || teamID < 0 || userID < 0){

            return new ResultT<Team>(ResponseCode.PARAMETER_ERROR.getCode(), ResponseCode.PARAMETER_ERROR.getMsg(), null);
        }

        // 数据库中取
        Team userInTeam = teamMapper.isUserInTeam(token, teamID, userID);

        if (userInTeam == null){

            return  new ResultT<Team>(ResponseCode.USER_NOT_IN_TEAM.getCode(), ResponseCode.USER_NOT_IN_TEAM.getMsg(), null);
        }

        // 成功返回
        return new ResultT<Team>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), userInTeam);
    }

    @Override
    public ResultT<List<Team>> getAllTeam(int page) {
        List<Team> allTeam = teamMapper.getAllTeam(page);
        List<Team> cleanTeams = new ArrayList<>();
        for (Team team : allTeam){
            Team cleanTeam = CleanUtil.cleanTeam(team);
            cleanTeams.add(cleanTeam);
        }
        return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),cleanTeams);
    }

    @Override
    public ResultT<Team> isContainName(String name) {
        Team team = teamMapper.isContainName(name);
        if (team != null){
            return new ResultT(ResponseCode.TEAM_NAME_USED.getCode(),ResponseCode.TEAM_NAME_USED.getMsg(),null);
        }
        return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),null);
    }

    @Override
    public ResultT createTeam(String teamName, String img, String motto, User user) {
        if (!(teamName.length() <= 12)){
            return new ResultT(ResponseCode.NAME_LENGTH_ERROR.getCode(),ResponseCode.NAME_LENGTH_ERROR.getMsg(),null);
        }
        ResultT<Team> msg = isContainName(teamName);
        if (msg.getStatus() != 200){
            return msg;
        }
        if (user.getTeamId() != 0){
            return new ResultT(ResponseCode.USER_HAS_IN_TEAM.getCode(),ResponseCode.USER_HAS_IN_TEAM.getMsg(),null);
        }
        Team team = new Team();
        user.setPassword(null);
        String token = UUID.randomUUID().toString().replace("-", "");
        team.setName(teamName);
        team.setMemberOne(user);
        team.setToken(token);
        if (!StringUtils.isEmpty(img)){
            team.setHeaderImg(img);
        }
        if (!StringUtils.isEmpty(motto)){
            team.setMotto(motto);
        }
        ResultT<Team> data = addTeam(team);
        return data;
    }

    @Override
    public ResultT<Team> updateTeam(String headerImg, String motto,User teamMember) {
        if (teamMember == null){
            return new ResultT(ResponseCode.LOGIN_FIRST_ERROR.getCode(),ResponseCode.LOGIN_FIRST_ERROR.getMsg(),null);
        }

        Team team = teamMember.getTeam();

        if (team == null){
            return new ResultT(ResponseCode.IN_A_TEAM_FIRST.getCode(),ResponseCode.IN_A_TEAM_FIRST.getMsg(),null);
        }


        if (StringUtils.isEmpty(headerImg)){
            team.setHeaderImg("https://geekplateform.oss-cn-beijing.aliyuncs.com/BaseHeaderImg.png?x-oss-process=image/auto-orient,1/quality,q_67");
        }
        else if (headerImg.equals("https://geekplateform.oss-cn-beijing.aliyuncs.com/BaseHeaderImg.png?x-oss-process=image/auto-orient,1/quality,q_67")){
            team.setHeaderImg(headerImg);
        }
        else{
            if (headerImg.length() >= 200){
                return new ResultT(ResponseCode.INPUT_LENGTH_TOO_LONG.getCode(),ResponseCode.INPUT_LENGTH_TOO_LONG.getMsg(),null);
            }
            headerImg = headerImg + "?x-oss-process=image/auto-orient,1/quality,q_67";
            team.setHeaderImg(headerImg);
        }



        if (StringUtils.isEmpty(motto)){
            team.setMotto(null);
        }else if (motto.length() > 20){
            return new ResultT(ResponseCode.MOTTO_LENGTH_TOO_LONG.getCode(),ResponseCode.MOTTO_LENGTH_TOO_LONG.getMsg(),null);
        }

        team.setMotto(motto);




        if (teamMapper.updateTeamInfo(team) == 0){
            return new ResultT(ResponseCode.TEAM_UPDATE_ERROR.getCode(),ResponseCode.TEAM_UPDATE_ERROR.getMsg(),null);
        }else{
            Team cleanTeam = CleanUtil.cleanTeamWithToken(team);
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),cleanTeam);
        }
    }

    @Override
    public ResultT checkCuit(User user) {
        Team team = user.getTeam();
        if (team == null){
            return new ResultT(ResponseCode.CREATE_TEAM_FIRST.getCode(),ResponseCode.CREATE_TEAM_FIRST.getMsg(),null);
        }
        team = CleanUtil.cleanTeam(team);
        if (team.getMemberOne().getIsCuit() == 1){
            if (team.getMemberTwo() != null){
                if (team.getMemberTwo().getIsCuit() == 1){
                    team.setIsCuit(1);
                    teamMapper.updateCuitTeam(team.getId());
                    return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),team);
                }else{
                    team.setIsCuit(0);
                    return new ResultT(ResponseCode.TEAM_NOT_CUIT.getCode(),ResponseCode.TEAM_NOT_CUIT.getMsg(),team);
                }
            }else{
                team.setIsCuit(1);
            }
            teamMapper.updateCuitTeam(team.getId());
            return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),team);
        }else{
            return new ResultT(ResponseCode.TEAM_NOT_CUIT.getCode(),ResponseCode.TEAM_NOT_CUIT.getMsg(),team);
        }
    }

    @Override
    public ResultT getData(User user) {
        if (user == null){
            return new ResultT(ResponseCode.LOGIN_FIRST_ERROR.getCode(),ResponseCode.LOGIN_FIRST_ERROR.getMsg(),null);
        }

        if (user.getTeamId() == 0){
            return new ResultT(ResponseCode.IN_A_TEAM_FIRST.getCode(),ResponseCode.IN_A_TEAM_FIRST.getMsg(),null);
        }

        Team team = teamMapper.getTeamById(user.getTeamId());
        String token = team.getToken();
        team = CleanUtil.cleanTeam(team);
        team.setToken(token);
        return new ResultT(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),team);
    }
}
