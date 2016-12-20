package ch.specchio.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ch.specchio.client.SPECCHIOClient;
import ch.specchio.client.SPECCHIOClientException;
import ch.specchio.client.SPECCHIOClientFactory;
import ch.specchio.client.SPECCHIOServerDescriptor;
import ch.specchio.queries.EAVQueryConditionObject;
import ch.specchio.queries.Query;
import ch.specchio.spaces.Space;
import ch.specchio.spaces.SpectralSpace;
import ch.specchio.types.attribute;

public class Test {

	public static void main(String[] args) throws SPECCHIOClientException,
			FileNotFoundException, IOException {
		
		double maxY = Double.MAX_VALUE;
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);
		maxY = Double.valueOf(df.format(maxY));
		System.out.println(maxY);
		
	}
}
