/*
 * Copyright (c) 2019 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */
/**
 *
 */
package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.LocalFile;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import com.automationanywhere.commandsdk.model.AttributeType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;


//BotCommand makes a class eligible for being considered as an action.
@BotCommand

//CommandPks adds required information to be dispalable on GUI.
@CommandPkg(
		//Unique name inside a package and label to display.
		name = "Convert_characode", label = "[[Characode.label]]",
		node_label = "[[Characode.node_label]]", description = "[[Characode.description]]", icon = "pkg.svg")
		
		//Return type information. return_type ensures only the right kind of variable is provided on the UI.
		//return_label = "[[Characode.return_label]]", return_type = STRING, return_required = true)

public class CharcodeConverter {
	
	//Messages read from full qualified property file name and provide i18n capability.
	private static final Messages MESSAGES = MessagesFactory
			.getMessages("com.automationanywhere.botcommand.samples.messages");

	//Identify the entry point for the action. Returns a Value<String> because the return type is String. 
	@Execute
	public void action(
			//変更元ファイル
			@Idx(index = "1", type = AttributeType.FILE)
			@Pkg(label = "[[Characode.File1.label]]")
			@NotEmpty
			@LocalFile
					String sOriginalFile,

			//変更元ファイルの言語コード
			@Idx(index = "2", type = AttributeType.SELECT, options = {
					@Idx.Option(index = "2.1", pkg = @Pkg(label = "[[Characode.option1.label]]", value = "Shift_JIS")),
					@Idx.Option(index = "2.2", pkg = @Pkg(label = "[[Characode.option2.label]]", value = "UTF-8")),
					@Idx.Option(index = "2.3", pkg = @Pkg(label = "[[Characode.option3.label]]", value = "EUC-JP"))
			})
			@Pkg(label = "[[Characode.option.label]]")
			@NotEmpty
					String sOriginalCode,

			//変更後ファイル
			@Idx(index = "3", type = TEXT)
			@Pkg(label = "[[Characode.File1.label]]")
			@NotEmpty
			@LocalFile
					String sTargetFile,

			//変更元ファイルの言語コード
			@Idx(index = "4", type = AttributeType.SELECT, options = {
					@Idx.Option(index = "4.1", pkg = @Pkg(label = "[[Characode.option1.label]]", value = "Shift_JIS")),
					@Idx.Option(index = "4.2", pkg = @Pkg(label = "[[Characode.option2.label]]", value = "UTF-8")),
					@Idx.Option(index = "4.3", pkg = @Pkg(label = "[[Characode.option4.label]]", value = "UTF-8BOM")),
					@Idx.Option(index = "4.4", pkg = @Pkg(label = "[[Characode.option3.label]]", value = "EUC-JP"))
			}
			)
			@Pkg(label = "[[Characode.option.label]]")
			@NotEmpty
					String sTargetCode
			) {

		//Internal validation, to disallow empty strings. No null check needed as we have NotEmpty on firstString.
		if (sTargetFile.equals(sOriginalFile))
			throw new BotCommandException("[[Characode.error1]]");

		if (sTargetCode.equals(sOriginalCode))
			throw new BotCommandException("[[Characode.error2]]");

		//Business logic
		try {
			conv(sOriginalFile,sOriginalCode,sTargetFile,sTargetCode);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Return StringValue.
		//return new StringValue();
	}

	//Add - start - 20200804
	//  ASCIIファイルだった時の処理を追加
	public void conv(String sOriginalFile, String sOriginalCode, String sTargetFile, String sTargetCode ) throws IOException {
		Path p = Path.of(sOriginalFile);
		Path o = Path.of(sTargetFile);

		//Add - start - 20201102
		//  To support converting to UTF-8 with BOM
		String tmp = "UTF-8BOM";
		if (sTargetCode.equals(tmp)){
			Files.write(o,new byte[]{ (byte)0xef,(byte)0xbb, (byte)0xbf });
			tmp = "UTF-8";
			Files.write( o, Files.readAllLines(p, Charset.forName(sOriginalCode)), Charset.forName(tmp), StandardOpenOption.APPEND);
		}
		else
		{
			Files.write( o, Files.readAllLines(p, Charset.forName(sOriginalCode)), Charset.forName(sTargetCode));
		}
		//Add - ednd - 20201102

	}
	//Add - end - 20200804

	public void xxxconv(String sOriginalFile, String sOriginalCode, String sTargetFile, String sTargetCode ) throws IOException {
		//Original file open
		File oFile = new File(sOriginalFile);
		FileReader filereader = new FileReader(oFile);
		BufferedReader reader = new BufferedReader(filereader);
		//String str = reader.readLine();

		//Target file open
		File tFile = new File(sTargetFile);
		PrintWriter pwriter = new PrintWriter(tFile);

		//Loope for Original file, convert code, add into Target file
		while (true){
			String line = reader.readLine();
			if(line != null){
				pwriter.println(new String(line.getBytes(sTargetCode),sTargetCode));
				pwriter.flush();
			}
			else
			{
				break;
			}
		}
	}



}
